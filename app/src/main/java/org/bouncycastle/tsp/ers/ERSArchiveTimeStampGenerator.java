package org.bouncycastle.tsp.ers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.util.Arrays;

public class ERSArchiveTimeStampGenerator {
    private final DigestCalculator digCalc;
    private List<ERSData> dataObjects = new ArrayList();
    private ERSRootNodeCalculator rootNodeCalculator = new BinaryTreeRootCalculator();

    public ERSArchiveTimeStampGenerator(DigestCalculator digestCalculator) {
        this.digCalc = digestCalculator;
    }

    private PartialHashtree[] getPartialHashtrees() {
        ERSDataGroup eRSDataGroup;
        List<byte[]> listBuildHashList = ERSUtil.buildHashList(this.digCalc, this.dataObjects);
        PartialHashtree[] partialHashtreeArr = new PartialHashtree[listBuildHashList.size()];
        HashSet hashSet = new HashSet();
        for (int i = 0; i != this.dataObjects.size(); i++) {
            if (this.dataObjects.get(i) instanceof ERSDataGroup) {
                hashSet.add((ERSDataGroup) this.dataObjects.get(i));
            }
        }
        for (int i2 = 0; i2 != listBuildHashList.size(); i2++) {
            byte[] bArr = listBuildHashList.get(i2);
            Iterator it = hashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    eRSDataGroup = null;
                    break;
                }
                eRSDataGroup = (ERSDataGroup) it.next();
                if (Arrays.areEqual(eRSDataGroup.getHash(this.digCalc), bArr)) {
                    List<byte[]> hashes = eRSDataGroup.getHashes(this.digCalc);
                    partialHashtreeArr[i2] = new PartialHashtree((byte[][]) hashes.toArray(new byte[hashes.size()][]));
                    break;
                }
            }
            if (eRSDataGroup == null) {
                partialHashtreeArr[i2] = new PartialHashtree(bArr);
            } else {
                hashSet.remove(eRSDataGroup);
            }
        }
        return partialHashtreeArr;
    }

    public void addAllData(List<ERSData> list) {
        this.dataObjects.addAll(list);
    }

    public void addData(ERSData eRSData) {
        this.dataObjects.add(eRSData);
    }

    public ERSArchiveTimeStamp generateArchiveTimeStamp(TimeStampResponse timeStampResponse) throws ERSException, TSPException {
        PartialHashtree[] partialHashtrees = getPartialHashtrees();
        byte[] bArrComputeRootHash = this.rootNodeCalculator.computeRootHash(this.digCalc, partialHashtrees);
        TSTInfo aSN1Structure = timeStampResponse.getTimeStampToken().getTimeStampInfo().toASN1Structure();
        if (!aSN1Structure.getMessageImprint().getHashAlgorithm().equals(this.digCalc.getAlgorithmIdentifier())) {
            throw new ERSException("time stamp imprint for wrong algorithm");
        }
        if (Arrays.areEqual(aSN1Structure.getMessageImprint().getHashedMessage(), bArrComputeRootHash)) {
            return new ERSArchiveTimeStamp(partialHashtrees.length == 1 ? new ArchiveTimeStamp(null, null, timeStampResponse.getTimeStampToken().toCMSSignedData().toASN1Structure()) : new ArchiveTimeStamp(this.digCalc.getAlgorithmIdentifier(), partialHashtrees, timeStampResponse.getTimeStampToken().toCMSSignedData().toASN1Structure()), this.digCalc, this.rootNodeCalculator);
        }
        throw new ERSException("time stamp imprint for wrong root hash");
    }

    public TimeStampRequest generateTimeStampRequest(TimeStampRequestGenerator timeStampRequestGenerator) throws TSPException, IOException {
        return timeStampRequestGenerator.generate(this.digCalc.getAlgorithmIdentifier(), this.rootNodeCalculator.computeRootHash(this.digCalc, getPartialHashtrees()));
    }

    public TimeStampRequest generateTimeStampRequest(TimeStampRequestGenerator timeStampRequestGenerator, BigInteger bigInteger) throws TSPException, IOException {
        return timeStampRequestGenerator.generate(this.digCalc.getAlgorithmIdentifier(), this.rootNodeCalculator.computeRootHash(this.digCalc, getPartialHashtrees()), bigInteger);
    }
}
