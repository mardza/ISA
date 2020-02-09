package com.isa.dto;

import java.util.Date;

public class PeriodDTO {
	
	private Long start;
	private Long end;
	
	
	public PeriodDTO() {}
	
	public PeriodDTO(Long start, Long end) {
		this.start = start;
		this.end = end;
	}

	
	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}
	
	public boolean in(PeriodDTO period) {
		return this.start >= period.start && this.end <= period.end;
	}

	@Override
	public String toString() {
		return "PeriodDTO [\n\tstart=" + new Date(start) + "\n\tend=" + new Date(end) + "\n]";
	}
}
