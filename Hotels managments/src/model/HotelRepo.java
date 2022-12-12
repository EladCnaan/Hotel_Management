package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry; 

public class HotelRepo implements Repos {

	private static HotelRepo single_instance=null;
	private String filename;
	public List<Hotel> hotels;
	
	private HotelRepo(String filename) {
		this.filename = filename;
		hotels = null;
		loadData();
		//saveData();
	}
	public static HotelRepo getInstance() {
		if(single_instance==null)
			single_instance=new HotelRepo("Hotels/hotels.txt");
		return single_instance;
	}
	
	public void fixHotelsDates(Date prev) {
		int i, j;
//	ADD NEW DATE
//		try{
//			hotels.get(0).roomTypes.get(0).calender.put(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-08"), 1);
//		}catch (Exception e) {
//			System.out.println("NOP");
//		}
//		System.out.println(hotels.get(0).roomTypes.get(0).calender.size());

		for(i = 0; i< hotels.size(); i++)
			for(j = 0; j< hotels.get(i).roomTypes.size(); j++)
			{
				for(Iterator<Entry<Date, Integer>> it = hotels.get(i).roomTypes.get(j).calender.entrySet().iterator(); it.hasNext(); ) {
				    Entry<Date, Integer> entry = it.next();
				    if(entry.getKey().before(prev))
				        it.remove();
			}
		}
		System.out.println("unnseesery data deleted");
}
	
	@SuppressWarnings("unchecked")
	public void loadData() {
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
			hotels = (List<Hotel>) in.readObject();
			for(Hotel h : hotels) {
				System.out.println(h.toString());
				System.out.println(h.getName() + " got " + h.roomTypes.size() + " roomtypes.");
			}
			System.out.println("HOTEL DATA LOADED");
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean emailUsed(String email) {
		for(int i = 0; i < hotels.size(); i++)
			if(email.equals(hotels.get(i).getMail()))
				return true;
		return false;
	}
	
	public Hotel find(String email) {
		for(int i = 0; i < hotels.size(); i++)
			if(email.equals(hotels.get(i).hotelManager.email))
				return hotels.get(i);
		return null;
	}
	
	public void addHotel(Hotel h) {
		hotels.add(h);
		System.out.println("HOTEL ADDED");
		saveData();
	}
	
	public void removeHotel(Hotel h) {
		hotels.remove(h);
		System.out.println("HOTEL REMOVED");
		saveData();
	}
	
	public void saveData() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
			//FOR RESETS
//			hotels = new ArrayList<Hotel>();
//			
//			hotels.add(new Hotel("#hotelName", "#address", "#phoneNumber", "#pass", "#mail", 5, "ya@ya.ya"));
//
//			hotels.get(0).addRoomType("21", 2, 32, 2, 3);

			out.writeObject(hotels);
			System.out.println("HOTELS DATA SAVED!");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}