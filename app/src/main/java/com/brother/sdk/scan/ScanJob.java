package com.brother.sdk.scan;

import android.content.Context;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.Job;
import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.scanner.ScanPaperSource;
import com.brother.sdk.common.device.scanner.ScanSpecialMode;
import com.brother.sdk.common.device.scanner.ScanType;
import com.brother.sdk.common.device.scanner.Scanner;
import com.brother.sdk.common.device.scanner.ScannerModelType;
import com.brother.sdk.common.presets.BrotherDeviceMasterSpec;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.ISocketConnector;
import com.brother.sdk.common.socket.scan.ScanImage;
import com.brother.sdk.common.socket.scan.ScanState;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandCallback;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandCheckDocument;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandClient;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandContext;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandDeviceConfiguration;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandEstimateDocumentSize;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandExecuteScan;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandGetScannerInformation;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandQueryDeviceConfiguration;
import com.brother.sdk.scan.ScanImageProcessor;
import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public class ScanJob extends Job {
    private static final int PROGRESS_SCANJOB_END = 100;
    private Context mContext;
    private ScanParameters mParameters;
    private ScanImageProcessor mProcessor;
    private Job.ProgressInterpolator mProgressInterpolator;
    private ScanJobController mScanJobController;
    private Device mDevice = null;
    private ISocketConnector mSocketConnector = null;
    private ScanCommandClient mClient = null;
    private ScanState mState = ScanState.ErrorScanUnknown;
    private ScanPaperSource mScanPaperSource = ScanPaperSource.AUTO;

    public ScanJob(ScanParameters scanParameters, Context context, ScanJobController scanJobController) {
        this.mProcessor = null;
        this.mParameters = null;
        this.mProgressInterpolator = null;
        this.mScanJobController = null;
        this.mContext = null;
        if (scanParameters == null || context == null || scanJobController == null) {
            throw new IllegalArgumentException("The arguments must not be null.");
        }
        this.mContext = context;
        this.mParameters = scanParameters;
        this.mScanJobController = scanJobController;
        this.mProgressInterpolator = new Job.ProgressInterpolator(this.mScanJobController);
        ScanImageProcessor.ScanImageProcessorContext scanImageProcessorContext = new ScanImageProcessor.ScanImageProcessorContext();
        scanImageProcessorContext.noJpegImageSaveQuality = 50;
        scanImageProcessorContext.duplex = this.mParameters.duplex;
        scanImageProcessorContext.whitePageRemove = this.mParameters.whitePageRemove;
        this.mProcessor = new ScanImageProcessor(this.mContext, scanImageProcessorContext, this.mScanJobController);
    }

    public ScanState getStatus() {
        return this.mState;
    }

    @Override
    public Job.JobState commit() {
        ScanCommandDeviceConfiguration scanCommandDeviceConfiguration;
        Job.JobState jobState = Job.JobState.ErrorJob;
        try {
            try {
                try {
                    try {
                        this.mState = ScanState.ErrorScanUnknown;
                        this.mProcessor.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Job.JobExecuteException e2) {
                    jobState = this.mState == ScanState.ErrorScanApplicationHostError ? Job.JobState.ErrorJobCancel : e2.state;
                    this.mProcessor.cancel();
                    ScanCommandClient scanCommandClient = this.mClient;
                    if (scanCommandClient != null) {
                        scanCommandClient.close();
                    }
                } catch (Exception unused) {
                    this.mState = ScanState.ErrorScanUnknown;
                    this.mProcessor.cancel();
                    ScanCommandClient scanCommandClient2 = this.mClient;
                    if (scanCommandClient2 != null) {
                        scanCommandClient2.close();
                    }
                }
            } catch (RuntimeException e3) {
                e3.printStackTrace();
                this.mProcessor.cancel();
                throw e3;
            } catch (ConnectException unused2) {
                this.mState = ScanState.ErrorScanConnectionFailure;
                this.mProcessor.cancel();
                ScanCommandClient scanCommandClient3 = this.mClient;
                if (scanCommandClient3 != null) {
                    scanCommandClient3.close();
                }
            }
            if (!verify(this.mDevice.scanner, this.mParameters)) {
                this.mState = ScanState.ErrorScanInvalidArgument;
                throw new Job.JobExecuteException(Job.JobState.ErrorJobParameterInvalid);
            }
            ScanCommandClient scanCommandClient4 = new ScanCommandClient(new ScanCommandCallback() {
                @Override
                public void onUpdateProcessProgress(int i) {
                    ScanJob.this.mProgressInterpolator.onUpdateProcessProgress(i);
                }

                @Override
                public void onNotifyProcessAlive() {
                    ScanJob.this.mProgressInterpolator.onNotifyProcessAlive();
                }

                @Override
                public void onScanImageRead(ScanImage scanImage) {
                    ScanJob.this.mProcessor.onScanImageRead(scanImage);
                }
            });
            this.mClient = scanCommandClient4;
            ISocket iSocketCreateConnectionSocket = this.mSocketConnector.createConnectionSocket(scanCommandClient4);
            ISocket.ConnectionType connectionType = iSocketCreateConnectionSocket.getConnectionType();
            if (!this.mClient.bind(iSocketCreateConnectionSocket)) {
                this.mState = ScanState.ErrorScanConnectionFailure;
                throw new Job.JobExecuteException(Job.JobState.ErrorJobConnectionFailure);
            }
            if (this.mDevice.scanner.hardwareErrors.contains(BrotherDeviceMasterSpec.ScanHardwareError.UselessBusinessCardAdfOption) && this.mParameters.documentSize == MediaSize.BusinessCard) {
                ScanState scanStateRequestCommand = this.mClient.requestCommand(new ScanCommandCheckDocument(ScanPaperSource.ADF));
                if (scanStateRequestCommand == ScanState.Success) {
                    this.mState = ScanState.ErrorScanInvalidADFBusinessCardScanRequest;
                    throw new Job.JobExecuteException();
                }
                if (scanStateRequestCommand == ScanState.ErrorScanApplicationHostError) {
                    this.mState = scanStateRequestCommand;
                    throw new Job.JobExecuteException();
                }
            }
            ScanCommandContext scanCommandContextCreateScanCommandContext = createScanCommandContext(this.mParameters, this.mDevice);
            if (connectionType == ISocket.ConnectionType.Network) {
                ScanCommandQueryDeviceConfiguration scanCommandQueryDeviceConfiguration = new ScanCommandQueryDeviceConfiguration();
                ScanState scanStateRequestCommand2 = this.mClient.requestCommand(scanCommandQueryDeviceConfiguration);
                this.mState = scanStateRequestCommand2;
                if (scanStateRequestCommand2 != ScanState.Success) {
                    throw new Job.JobExecuteException();
                }
                scanCommandDeviceConfiguration = scanCommandQueryDeviceConfiguration.getResult();
            } else {
                scanCommandDeviceConfiguration = new ScanCommandDeviceConfiguration(this.mDevice.scanner);
            }
            ScanCommandContext scanCommandContextValidate = scanCommandDeviceConfiguration.validate(scanCommandContextCreateScanCommandContext);
            if (scanCommandContextValidate.scanProtocol == ScanCommandParameters.ProtocolGeneration.ScanProtocol_Undefined) {
                this.mState = ScanState.ErrorScanNoSupportedProtocol;
                throw new Job.JobExecuteException(Job.JobState.ErrorJobParameterInvalid);
            }
            ScanCommandGetScannerInformation scanCommandGetScannerInformation = new ScanCommandGetScannerInformation(scanCommandContextValidate);
            ScanState scanStateRequestCommand3 = this.mClient.requestCommand(scanCommandGetScannerInformation);
            this.mState = scanStateRequestCommand3;
            if (scanStateRequestCommand3 != ScanState.Success) {
                throw new Job.JobExecuteException();
            }
            ScanCommandParameters.ScannerInformation result = scanCommandGetScannerInformation.getResult();
            this.mScanPaperSource = result.mSource;
            scanCommandContextValidate.resolution = result.mResolution;
            if (this.mScanPaperSource.equals(ScanPaperSource.ADF) && scanCommandContextValidate.documentSize.equals(MediaSize.BusinessCardLandscape)) {
                List<MediaSize> list = this.mDevice.scanner.capabilities.documentSizes.get(ScanType.ADFSimplexScan);
                if (list == null || !list.contains(MediaSize.BusinessCard)) {
                    this.mState = ScanState.ErrorScanInvalidADFBusinessCardScanRequest;
                    throw new Job.JobExecuteException(Job.JobState.ErrorJobParameterInvalid);
                }
                scanCommandContextValidate.setDocumentSize(MediaSize.BusinessCard);
            }
            if (scanCommandContextValidate.AutoDocumentSizeScan) {
                ScanCommandEstimateDocumentSize scanCommandEstimateDocumentSize = new ScanCommandEstimateDocumentSize(scanCommandContextValidate);
                ScanState scanStateRequestCommand4 = this.mClient.requestCommand(scanCommandEstimateDocumentSize);
                this.mState = scanStateRequestCommand4;
                if (scanStateRequestCommand4 != ScanState.Success) {
                    throw new Job.JobExecuteException();
                }
                MediaSize result2 = scanCommandEstimateDocumentSize.getResult();
                if (scanCommandContextValidate.duplex != Duplex.Simplex && (result2 == MediaSize.A3 || result2 == MediaSize.B4 || result2 == MediaSize.Ledger)) {
                    this.mState = ScanState.ErrorScanInvalidAutoScanDuplexRequest;
                    throw new Job.JobExecuteException();
                }
                scanCommandContextValidate.setDocumentSize(result2);
            }
            scanCommandContextValidate.finalizeScanArea(result);
            this.mProgressInterpolator.startInterpolateProgressCountUntil(100);
            ScanState scanStateRequestCommand5 = this.mClient.requestCommand(new ScanCommandExecuteScan(scanCommandContextValidate));
            this.mState = scanStateRequestCommand5;
            if (scanStateRequestCommand5 != ScanState.Success) {
                throw new Job.JobExecuteException();
            }
            this.mProcessor.finish();
            jobState = Job.JobState.SuccessJob;
            ScanCommandClient scanCommandClient5 = this.mClient;
            if (scanCommandClient5 != null) {
                scanCommandClient5.close();
            }
            return jobState;
        } catch (Throwable th) {
            ScanCommandClient scanCommandClient6 = this.mClient;
            if (scanCommandClient6 != null) {
                try {
                    scanCommandClient6.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    @Override
    public boolean bind(IConnector iConnector) {
        if (iConnector != null) {
            this.mDevice = iConnector.getDevice();
            IUnknown iUnknownQueryInterface = iConnector.queryInterface(ISocketConnector.f506ID);
            if (iUnknownQueryInterface != null) {
                this.mSocketConnector = (ISocketConnector) iUnknownQueryInterface;
                return true;
            }
        }
        return false;
    }

    @Override
    public void cancel() {
        ScanCommandClient scanCommandClient = this.mClient;
        if (scanCommandClient != null) {
            try {
                scanCommandClient.cancel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.mProcessor.cancel();
    }

    private boolean verify(Scanner scanner, ScanParameters scanParameters) {
        try {
            if (scanner == null || scanParameters == null) {
                throw new Exception("scanner or parameters is null.");
            }
            if (!scanner.autoDocumentSizeScanSupport && ((scanner.modelType != ScannerModelType.DocumentScanner || !scanner.capabilities.specialScanModes.contains(ScanSpecialMode.CORNER_SCAN)) && scanParameters.autoDocumentSizeScan)) {
                throw new Exception("Auto document size scan is not supported.");
            }
            if (!scanner.capabilities.duplices.contains(scanParameters.duplex)) {
                throw new Exception("Scan duplex is not supported.");
            }
            if (!scanner.capabilities.paperSources.contains(scanParameters.paperSource)) {
                throw new Exception("Specified paper source is not supported.");
            }
            if (scanParameters.paperSource == ScanPaperSource.ADF) {
                if (scanParameters.duplex != Duplex.Simplex) {
                    if (!scanner.capabilities.documentSizes.get(ScanType.ADFDuplexScan).contains(scanParameters.documentSize)) {
                        throw new Exception("Specified document size is not supported for duplex.");
                    }
                } else if (!scanner.capabilities.documentSizes.get(ScanType.ADFSimplexScan).contains(scanParameters.documentSize)) {
                    throw new Exception("Specified document size is not supported for duplex off.");
                }
            } else if (scanParameters.paperSource == ScanPaperSource.FB && !scanner.capabilities.documentSizes.get(ScanType.FlatbedScan).contains(scanParameters.documentSize)) {
                throw new Exception("Specified document size is not supported for flatbed.");
            }
            if (!scanner.capabilities.colorTypes.contains(scanParameters.colorType)) {
                throw new Exception("Specified color type (" + scanParameters.colorType.toString() + ") is not supported.");
            }
            if (scanner.capabilities.resolutions.contains(scanParameters.resolution)) {
                return true;
            }
            throw new Exception("Specified resolution is not supported.");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private ScanCommandContext createScanCommandContext(ScanParameters scanParameters, Device device) {
        ScanCommandContext scanCommandContext = new ScanCommandContext();
        scanCommandContext.scanProtocol = ScanCommandParameters.ProtocolGeneration.fromValue(device.scanner.protocolVersion);
        scanCommandContext.Region = device.countrySpec;
        scanCommandContext.modelSize = device.scanner.modelSize;
        scanCommandContext.modelType = device.scanner.modelType;
        scanCommandContext.hFBAlignment = device.scanner.modelHorizontalAlignment;
        scanCommandContext.hADFAlignment = device.scanner.modelHorizontalAlignment;
        scanCommandContext.vFBAlignment = device.scanner.modelVerticalAlignment;
        scanCommandContext.vADFAlignment = device.scanner.modelVerticalAlignment;
        scanCommandContext.contrast = 50;
        scanCommandContext.brightness = 50;
        scanCommandContext.duplex = this.mParameters.duplex;
        scanCommandContext.resolution = new ScanCommandParameters.ScanResolution(this.mParameters.resolution.width, this.mParameters.resolution.height);
        scanCommandContext.paperSource = scanParameters.paperSource;
        scanCommandContext.setDocumentSize(this.mParameters.documentSize);
        if (C07572.$SwitchMap$com$brother$sdk$common$device$ColorProcessing[scanParameters.colorType.ordinal()] != 2) {
            scanCommandContext.Mode = ScanCommandParameters.ScanMode.CGRAY;
            scanCommandContext.Compression = ScanCommandParameters.DataCompression.JPEG;
        } else {
            scanCommandContext.Mode = ScanCommandParameters.ScanMode.TEXT;
            scanCommandContext.Compression = ScanCommandParameters.DataCompression.NONE;
        }
        if (scanCommandContext.modelType == ScannerModelType.DocumentScanner) {
            scanCommandContext.Special = this.mParameters.autoDocumentSizeScan ? ScanSpecialMode.CORNER_SCAN : scanParameters.specialScanMode;
            scanCommandContext.AutoDocumentSizeScan = false;
            scanCommandContext.Compression = ScanCommandParameters.DataCompression.JPEG;
            scanCommandContext.Jpeg = ScanCommandParameters.JpegCompression.MID;
            if (C07572.$SwitchMap$com$brother$sdk$common$device$ColorProcessing[scanParameters.colorType.ordinal()] != 2) {
                scanCommandContext.Mode = ScanCommandParameters.ScanMode.CGRAY;
            } else {
                scanCommandContext.Mode = ScanCommandParameters.ScanMode.TEXT;
                scanCommandContext.Compression = ScanCommandParameters.DataCompression.NONE;
            }
        } else {
            scanCommandContext.Special = scanParameters.specialScanMode;
            scanCommandContext.AutoDocumentSizeScan = this.mParameters.autoDocumentSizeScan;
            if (C07572.$SwitchMap$com$brother$sdk$common$device$ColorProcessing[scanParameters.colorType.ordinal()] != 2) {
                scanCommandContext.Mode = ScanCommandParameters.ScanMode.CGRAY;
                scanCommandContext.Compression = ScanCommandParameters.DataCompression.JPEG;
                scanCommandContext.Jpeg = ScanCommandParameters.JpegCompression.MID;
            } else {
                scanCommandContext.Mode = ScanCommandParameters.ScanMode.TEXT;
                scanCommandContext.Compression = ScanCommandParameters.DataCompression.NONE;
            }
        }
        if (this.mParameters.groundColorCorrection) {
            scanCommandContext.groundColorCorrectionLevel = 0;
        }
        scanCommandContext.validate();
        return scanCommandContext;
    }

    static class C07572 {
        static final int[] $SwitchMap$com$brother$sdk$common$device$ColorProcessing;

        static {
            int[] iArr = new int[ColorProcessing.values().length];
            $SwitchMap$com$brother$sdk$common$device$ColorProcessing = iArr;
            try {
                iArr[ColorProcessing.FullColor.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$ColorProcessing[ColorProcessing.BlackAndWhite.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ScanPaperSource getScanPaperSource() {
        return this.mScanPaperSource;
    }
}
