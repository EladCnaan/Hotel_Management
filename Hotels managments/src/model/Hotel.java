package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String hotelName;
	private String address;
	private String phoneNumber;
	private String mail;
	private int stars;
	public HotelManager hotelManager;
	public double total;
	public List<RoomType> roomTypes;
	
	public Hotel(String hotelName, String address, String phoneNumber, String password, String mail, int stars, String managerMail) {
		this.hotelName = hotelName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.stars = stars;
		this.total = 0;
		this.roomTypes = new ArrayList<RoomType>();
		this.hotelManager = new HotelManager(null, null, null, managerMail, null, false, password, 10, 10, 10);
	}
	
	public void addRoomType(String typeName, int capacity, double price, double size, int amount) {
		roomTypes.add(new RoomType(typeName ,capacity, price, size, amount));
	}
	
	
	public String getMail() {
		return this.mail;
	}
	
	public String getName() {
		return hotelName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phoneNumber;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void addToTotal(int num) {
		total += num;
	}
	
	public void editHotel(String hotelName, String address, String phone) {
		this.hotelName = hotelName;
		this.address = address;
		this.phoneNumber = phone;
	}
	
	@Override
	public String toString() {
		return "<html>The "+ hotelName + " hotel got " + String.valueOf(stars) + " stars.<br>The address is " + address + ".<br>And the phone is " + phoneNumber + ".<html>";
	}
}