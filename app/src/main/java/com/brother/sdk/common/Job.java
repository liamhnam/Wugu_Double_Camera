package com.brother.sdk.common;

public abstract class Job {

    public enum JobState {
        SuccessJob,
        ErrorJob,
        ErrorJobConnectionFailure,
        ErrorJobParameterInvalid,
        ErrorJobCancel
    }

    public abstract boolean bind(IConnector iConnector);

    public abstract void cancel();

    public abstract JobState commit();

    public static class ProgressInterpolator extends Callback {
        static final int END = 100;
        Callback mInterpolateTarget;
        int mStart = 0;
        int mInterpolatePoint = 1;

        public ProgressInterpolator(Callback callback) {
            this.mInterpolateTarget = callback;
        }

        public void startInterpolateProgressCountUntil(int i) {
            if (i > 100) {
                i = 100;
            }
            this.mInterpolateTarget.onUpdateProcessProgress(this.mInterpolatePoint);
            this.mStart = this.mInterpolatePoint;
            this.mInterpolatePoint = i;
        }

        @Override
        public void onUpdateProcessProgress(int i) {
            this.mInterpolateTarget.onUpdateProcessProgress(this.mStart + ((int) ((this.mInterpolatePoint - r0) * (i / 100.0f))));
        }

        @Override
        public void onNotifyProcessAlive() {
            this.mInterpolateTarget.onNotifyProcessAlive();
        }
    }

    protected static class JobExecuteException extends Exception {
        private static final long serialVersionUID = -1418027003955579795L;
        public JobState state;

        public JobExecuteException(JobState jobState) {
            this.state = jobState;
        }

        public JobExecuteException() {
            this.state = JobState.ErrorJob;
        }
    }
}
