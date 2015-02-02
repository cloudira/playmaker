package org.cloudira.playmaker.actuator.client.resource;

import java.util.Arrays;

public class ThreadInfoResource {

	private String threadName;
	private long threadId;
	private long blockedTime;
	private long blockedCount;
	private long waitedTime;
	private long waitedCount;

	private String lockName;
	private long lockOwnerId;
	private String lockOwnerName;
	private boolean inNative;
	private boolean suspended;
	private Thread.State threadState;
	private StackTraceElementResource[] stackTrace;
	private MonitorInfoResource[] lockedMonitors;
	private LockInfoResource[] lockedSynchronizers;
	private LockInfoResource lockInfo;

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public long getThreadId() {
		return threadId;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	public long getBlockedTime() {
		return blockedTime;
	}

	public void setBlockedTime(long blockedTime) {
		this.blockedTime = blockedTime;
	}

	public long getBlockedCount() {
		return blockedCount;
	}

	public void setBlockedCount(long blockedCount) {
		this.blockedCount = blockedCount;
	}

	public long getWaitedTime() {
		return waitedTime;
	}

	public void setWaitedTime(long waitedTime) {
		this.waitedTime = waitedTime;
	}

	public long getWaitedCount() {
		return waitedCount;
	}

	public void setWaitedCount(long waitedCount) {
		this.waitedCount = waitedCount;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public long getLockOwnerId() {
		return lockOwnerId;
	}

	public void setLockOwnerId(long lockOwnerId) {
		this.lockOwnerId = lockOwnerId;
	}

	public String getLockOwnerName() {
		return lockOwnerName;
	}

	public void setLockOwnerName(String lockOwnerName) {
		this.lockOwnerName = lockOwnerName;
	}

	public boolean isInNative() {
		return inNative;
	}

	public void setInNative(boolean inNative) {
		this.inNative = inNative;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public Thread.State getThreadState() {
		return threadState;
	}

	public void setThreadState(Thread.State threadState) {
		this.threadState = threadState;
	}

	public StackTraceElementResource[] getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(StackTraceElementResource[] stackTrace) {
		this.stackTrace = stackTrace;
	}

	public MonitorInfoResource[] getLockedMonitors() {
		return lockedMonitors;
	}

	public void setLockedMonitors(MonitorInfoResource[] lockedMonitors) {
		this.lockedMonitors = lockedMonitors;
	}

	public LockInfoResource[] getLockedSynchronizers() {
		return lockedSynchronizers;
	}

	public void setLockedSynchronizers(LockInfoResource[] lockedSynchronizers) {
		this.lockedSynchronizers = lockedSynchronizers;
	}

	public LockInfoResource getLockInfo() {
		return lockInfo;
	}

	public void setLockInfo(LockInfoResource lockInfo) {
		this.lockInfo = lockInfo;
	}

	public static class LockInfoResource {
		private String className;
		private int identityHashCode;

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public int getIdentityHashCode() {
			return identityHashCode;
		}

		public void setIdentityHashCode(int identityHashCode) {
			this.identityHashCode = identityHashCode;
		}

		@Override
		public String toString() {
			return "LockInfoResource [className=" + className + ", identityHashCode=" + identityHashCode + "]";
		}

	}

	public static class MonitorInfoResource extends LockInfoResource {
		private int lockedStackDepth;
		private StackTraceElementResource lockedStackFrame;

		public int getLockedStackDepth() {
			return lockedStackDepth;
		}

		public void setLockedStackDepth(int lockedStackDepth) {
			this.lockedStackDepth = lockedStackDepth;
		}

		public StackTraceElementResource getLockedStackFrame() {
			return lockedStackFrame;
		}

		public void setLockedStackFrame(StackTraceElementResource lockedStackFrame) {
			this.lockedStackFrame = lockedStackFrame;
		}

		@Override
		public String toString() {
			return "MonitorInfoResource [lockedStackDepth=" + lockedStackDepth + ", lockedStackFrame=" + lockedStackFrame + ", getClassName()=" + getClassName() + ", getIdentityHashCode()=" + getIdentityHashCode() + "]";
		}
	}

	public static class StackTraceElementResource {
		private String methodName;
		private String fileName;
		private int lineNumber;
		private String className;
		private boolean nativeMethod;

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public int getLineNumber() {
			return lineNumber;
		}

		public void setLineNumber(int lineNumber) {
			this.lineNumber = lineNumber;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public boolean isNativeMethod() {
			return nativeMethod;
		}

		public void setNativeMethod(boolean nativeMethod) {
			this.nativeMethod = nativeMethod;
		}

		@Override
		public String toString() {
			return "StackTraceElementResource [methodName=" + methodName + ", fileName=" + fileName + ", lineNumber=" + lineNumber + ", className=" + className + ", nativeMethod=" + nativeMethod + "]";
		}
	}

	@Override
	public String toString() {
		return "ThreadInfoResource [threadName=" + threadName + ", threadId=" + threadId + ", blockedTime=" + blockedTime + ", blockedCount=" + blockedCount + ", waitedTime=" + waitedTime + ", waitedCount=" + waitedCount + ", lockName=" + lockName + ", lockOwnerId=" + lockOwnerId
				+ ", lockOwnerName=" + lockOwnerName + ", inNative=" + inNative + ", suspended=" + suspended + ", threadState=" + threadState + ", stackTrace=" + Arrays.toString(stackTrace) + ", lockedMonitors=" + Arrays.toString(lockedMonitors) + ", lockedSynchronizers="
				+ Arrays.toString(lockedSynchronizers) + ", lockInfo=" + lockInfo + "]";
	}

}
