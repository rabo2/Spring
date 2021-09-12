package com.personal.farmer.dto;

public class ShipmentVO {
	private String ship_no;
	private String prod_id;
	private String mem_id;
	private int ship_qty;
	private String ship_date;
	
	public String getShip_no() {
		return ship_no;
	}
	public void setShip_no(String ship_no) {
		this.ship_no = ship_no;
	}
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
	public int getShip_qty() {
		return ship_qty;
	}
	public void setShip_qty(int ship_qty) {
		this.ship_qty = ship_qty;
	}
	public String getShip_date() {
		return ship_date;
	}
	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
	}
	
	
}
