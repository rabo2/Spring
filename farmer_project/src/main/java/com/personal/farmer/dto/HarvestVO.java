package com.personal.farmer.dto;

public class HarvestVO {
	private String prod_id;
	private String mem_id;
	private String hav_date;
	private int hav_qty;
	
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getHav_date() {
		return hav_date;
	}
	public void setHav_date(String hav_date) {
		this.hav_date = hav_date;
	}
	public int getHav_qty() {
		return hav_qty;
	}
	public void setHav_qty(int hav_qty) {
		this.hav_qty = hav_qty;
	}
}
