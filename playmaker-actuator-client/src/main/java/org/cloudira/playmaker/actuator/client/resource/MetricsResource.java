package org.cloudira.playmaker.actuator.client.resource;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class MetricsResource {
	
	private Long mem;
	@JsonProperty("mem.free")
	private Long memFree;
	private Integer processors;
	private Long uptime;
	@JsonProperty("instance.uptime")
	private Long instanceUptime;
	@JsonProperty("systemload.average")
	private Double systemloadAverage;
	
	@JsonUnwrapped
	private Heap heapDetails;
	
	@JsonUnwrapped
	private Threads threadsDetails;
	
	@JsonUnwrapped
	private Classes classesDetails;
	
	private Map<String, Object> details = new HashMap<>();

	public Long getMem() {
		return mem;
	}

	public void setMem(Long mem) {
		this.mem = mem;
	}

	public Long getMemFree() {
		return memFree;
	}

	public void setMemFree(Long memFree) {
		this.memFree = memFree;
	}

	public Integer getProcessors() {
		return processors;
	}

	public void setProcessors(Integer processors) {
		this.processors = processors;
	}

	public Long getUptime() {
		return uptime;
	}

	public void setUptime(Long uptime) {
		this.uptime = uptime;
	}

	public Long getInstanceUptime() {
		return instanceUptime;
	}

	public void setInstanceUptime(Long instanceUptime) {
		this.instanceUptime = instanceUptime;
	}

	public Double getSystemloadAverage() {
		return systemloadAverage;
	}

	public void setSystemloadAverage(Double systemloadAverage) {
		this.systemloadAverage = systemloadAverage;
	}

	public Heap getHeapDetails() {
		return heapDetails;
	}
	
	public void setHeapDetails(Heap heapDetails) {
		this.heapDetails = heapDetails;
	}
	
	public Threads getThreadsDetails() {
		return threadsDetails;
	}
	
	public void setThreadsDetails(Threads threadsDetails) {
		this.threadsDetails = threadsDetails;
	}

	public Classes getClassesDetails() {
		return classesDetails;
	}
	
	public void setClassesDetails(Classes classesDetails) {
		this.classesDetails = classesDetails;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getDetails() {
		return details;
	}

	@JsonAnySetter
	public void setDetails(String key, Object value) {
		getDetails().put(key, value);
	}
	
	public static class Heap {
		@JsonProperty("heap.committed")
		private Long heapCommitted;
		@JsonProperty("heap.init")
		private Long heapInit;
		@JsonProperty("heap.used")
		private Long heapUsed;
		private Long heap;
		
		public Long getHeapCommitted() {
			return heapCommitted;
		}
		public void setHeapCommitted(Long heapCommitted) {
			this.heapCommitted = heapCommitted;
		}
		public Long getHeapInit() {
			return heapInit;
		}
		public void setHeapInit(Long heapInit) {
			this.heapInit = heapInit;
		}
		public Long getHeapUsed() {
			return heapUsed;
		}
		public void setHeapUsed(Long heapUsed) {
			this.heapUsed = heapUsed;
		}
		public Long getHeap() {
			return heap;
		}
		public void setHeap(Long heap) {
			this.heap = heap;
		}
		@Override
		public String toString() {
			return "Heap [heapCommitted=" + heapCommitted + ", heapInit=" + heapInit + ", heapUsed=" + heapUsed + ", heap=" + heap + "]";
		}
	}
	
	public static class Classes {
		private Long classes;
		@JsonProperty("classes.loaded")
		private Long classesLoaded;
		@JsonProperty("classes.unloaded")
		private Long classesUnloaded;
		
		public Long getClasses() {
			return classes;
		}

		public void setClasses(Long classes) {
			this.classes = classes;
		}

		public Long getClassesLoaded() {
			return classesLoaded;
		}

		public void setClassesLoaded(Long classesLoaded) {
			this.classesLoaded = classesLoaded;
		}

		public Long getClassesUnloaded() {
			return classesUnloaded;
		}

		public void setClassesUnloaded(Long classesUnloaded) {
			this.classesUnloaded = classesUnloaded;
		}

		@Override
		public String toString() {
			return "Classes [classes=" + classes + ", classesLoaded=" + classesLoaded + ", classesUnloaded=" + classesUnloaded + "]";
		}
	}

	public static class Threads {
		@JsonProperty("threads.peak")
		private Long peak;
		@JsonProperty("threads.daemon")
		private Long daemon;
		@JsonProperty("threads")
		private Long threads;
		
		public Long getPeak() {
			return peak;
		}
		
		public void setPeak(Long peak) {
			this.peak = peak;
		}
		
		public Long getDaemon() {
			return daemon;
		}
		
		public void setDaemon(Long daemon) {
			this.daemon = daemon;
		}
		
		public Long getThreads() {
			return threads;
		}
		
		public void setThreads(Long threads) {
			this.threads = threads;
		}

		@Override
		public String toString() {
			return "Threads [peak=" + peak + ", daemon=" + daemon + ", threads=" + threads + "]";
		}
	}
	
	public static class Gc {
		
		private Map<String, Long> details = new HashMap<>();
		
		@JsonAnyGetter
		public Map<String, Long> getDetails() {
			return details;
		}
		
		@JsonAnySetter
		public void setDetail(String key, Long value) {
			getDetails().put(key, value);
		}
		
		@Override
		public String toString() {
			return getDetails().toString();
		}
	}

	@Override
	public String toString() {
		return "MetricsResource [mem=" + mem + ", memFree=" + memFree + ", processors=" + processors + ", uptime=" + uptime + ", instanceUptime=" + instanceUptime + ", systemloadAverage=" + systemloadAverage + ", heapDetails=" + heapDetails + ", threadsDetails=" + threadsDetails
				+ ", classesDetails=" + classesDetails + ", details=" + details + "]";
	}
	
	
	
	
	
	
}
