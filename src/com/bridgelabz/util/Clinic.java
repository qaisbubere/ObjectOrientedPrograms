package com.bridgelabz.util;


	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Scanner;
	import org.json.simple.JSONArray;
	import org.json.simple.JSONObject;
	import org.json.simple.parser.JSONParser;
	import org.json.simple.parser.ParseException;
	import com.bridgelabz.programs.Doctor;
	import com.bridgelabz.programs.Patient;

	public class Clinic {


		private static Scanner scanner = new Scanner(System.in);
		private JSONObject jsonObjectDoctor;
		private JSONObject jsonObjectPatient;
		private JSONObject jsonObjectAppointment;
		private JSONParser parser = new JSONParser();
		private String doctorFilePath;
		private String patientFilePath;
		private String appointmentFilePath;
		private JSONArray doctors;
		private JSONArray patients;
		private JSONArray appointment;
		private String doctorArrayName;
		private String patientArrayName;
		private String appointmentArrayName;

		/*
		 * parameterized constructor
		 */
		public Clinic(String doctorFile, String patientFile, String appointmentFile) {
			this.doctorFilePath = doctorFile;
			this.patientFilePath = patientFile;
			this.appointmentFilePath = appointmentFile;
			jsonObjectDoctor = new JSONObject();
			jsonObjectPatient = new JSONObject();
			jsonObjectAppointment = new JSONObject();
			doctors = new JSONArray();
			patients = new JSONArray();
			appointment = new JSONArray();
			doctorArrayName = "Doctors";
			patientArrayName = "Patients";
			appointmentArrayName = "Appointment";
		}

		/*
		 * method to get name of doctor array
		 */
		private String getDoctorArrayName() {
			return doctorArrayName;
		}

		/*
		 * method to get name of patient array
		 */
		private String getPatientArrayName() {
			return patientArrayName;
		}

		/*
		 * method to get name of appointment array
		 */
		private String getAppointmentArrayName() {
			return appointmentArrayName;
		}

		/*
		 * method to add doctor
		 */
		public void addDoctor(String doctorFile) throws FileNotFoundException, IOException, ParseException {
			File file = new File(doctorFile);
			if (file.length() == 0) {
				getDoctorEntry(doctors);
			} else {
				doctors = getArray(doctorFile, getDoctorArrayName());
				getDoctorEntry(doctors);
			}
		}

		/*
		 * method for getting details of doctor
		 */
		private void getDoctorEntry(JSONArray doctors) throws IOException {
			Doctor doctorInformation = new Doctor();
			System.out.println("Enter the name of the doctor");
			doctorInformation.setDoctorName(scanner.next());
			System.out.println("Enter the specialization of the doctor");
			doctorInformation.setSpecialization(scanner.next());
			System.out.println("Enter the availability of the doctor");
			doctorInformation.setAvailability(scanner.next());
			createJsonObject(doctorInformation, doctors);
		}

		@SuppressWarnings("unchecked")
		/*
		 * method for writing doctors information to the file
		 */
		private void createJsonObject(Doctor doctorInformation, JSONArray doctors) throws IOException {
			JSONObject object = new JSONObject();
			object.put("DoctorName", doctorInformation.getDoctorName());
			object.put("DoctorId", Integer.toString(doctors.size() + 1));
			object.put("Specialization", doctorInformation.getSpecialization());
			object.put("Availability", doctorInformation.getAvailability());
			doctors.add(object);
			jsonObjectDoctor.put(getDoctorArrayName(), doctors);
			writeToFile(jsonObjectDoctor, doctorFilePath);
		}

		/*
		 * method for adding patient
		 */
		public void addPatient(String patientFile) throws FileNotFoundException, IOException, ParseException {
			File file = new File(patientFile);
			if (file.length() == 0) {
				getPatientEntry(patients);
			} else {
				patients = getArray(patientFile, getPatientArrayName());
				getPatientEntry(patients);
			}
		}

		/*
		 * method for getting details of patient
		 */
		private void getPatientEntry(JSONArray patients) throws IOException {
			Patient patientInformation = new Patient();
			System.out.println("Enter the name of the patient");
			patientInformation.setPatientName(scanner.next());
			System.out.println("Enter the age of the patient");
			patientInformation.setPatientAge(scanner.nextInt());
			System.out.println("Enter the patient's phone Number");
			patientInformation.setPhoneNumber(scanner.nextLong());
			createJsonObject(patientInformation, patients);
		}

		@SuppressWarnings("unchecked")
		/*
		 * method for writing details of patient into file
		 */
		private void createJsonObject(Patient patientInformation, JSONArray patients) throws IOException {
			JSONObject object = new JSONObject();
			object.put("PatientName", patientInformation.getPatientName());
			object.put("PatientId", Integer.toString(patients.size() + 1));
			object.put("PatientAge", patientInformation.getPatientAge());
			object.put("MobileNumber", patientInformation.getPhoneNumber());
			patients.add(object);
			jsonObjectPatient.put(getPatientArrayName(), patients);
			writeToFile(jsonObjectPatient, patientFilePath);
		}

		@SuppressWarnings("resource")
		/*
		 * method for writing details to file
		 */
		private void writeToFile(JSONObject jsonObject, String filePath) throws IOException {
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.write(jsonObject.toJSONString());
			fileWriter.flush();
		}

		/*
		 * method to get array of given name
		 */
		public JSONArray getArray(String fileName, String arrayName)
				throws FileNotFoundException, IOException, ParseException {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
			JSONArray array = (JSONArray) jsonObject.get(arrayName);
			return array;
		}

		/*
		 * method for maintaining appointments of patients with the doctors
		 */
		public void appointment(String appointmentFile) throws FileNotFoundException, IOException, ParseException {
			File file = new File(appointmentFilePath);
			JSONArray array;
			System.out.println("Enter name of the patient");
			String patientName = scanner.next();
			String doctorName = searchDoctor(patientName);
			if (doctorName != "$") {
				if (file.length() == 0) {
					addToAppointmentFile(doctorName, patientName);
				} else {
					array = getAppointmentArray(appointmentFilePath, "Appointment", doctorName, patientName);
					if (!checkPatientName(array, getAppointmentArrayName(), appointmentFilePath, patientName))
						updateAppointmentFile(doctorName, patientName, array);
				}
			} else {
				System.out.println("Sorry..!!No such doctor is present in this clinic");
			}
		}

		/*
		 * method for searching doctors through its various attributes
		 */
		private String searchDoctor(String patientName) throws FileNotFoundException, IOException, ParseException {
			String name = "";
			char reply = ' ';
			do {
				System.out.println("Search Doctor by \n 1.Name\n 2.Id \n 3.Specialization \n 4.Availability");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter Doctor Name");
					String doctorName = scanner.next();
					name = search("DoctorName", doctorName, patientName);
					return name;
				case 2:
					System.out.println("Enter Doctor Id");
					String id = Integer.toString(scanner.nextInt());
					name = search("DoctorId", id, patientName);
					return name;
				case 3:
					System.out.println("Enter Specialization");
					String specialization = scanner.next();
					name = search("Specialization", specialization, patientName);
					return name;
				case 4:
					System.out.println("Enter Availability");
					String availability = scanner.next();
					name = search("Availability", availability, patientName);
					return name;
				default:
					System.out.println("Invalid choice");
				}
				System.out.println("Would you like to choose again..");
				reply = scanner.next().charAt(0);
			} while (reply == 'Y' || reply == 'y');
			return name;
		}

		/*
		 * method for getting a particular doctors name from doctor array
		 */
		private String search(String searchBy, String search, String patientName)
				throws FileNotFoundException, IOException, ParseException {
			String doctorName = "$";
			JSONObject obj = null;
			JSONArray patientArray;
			JSONArray doctorArray = getArray(doctorFilePath, getDoctorArrayName());
			for (int i = 0; i < doctorArray.size(); i++) {
				obj = (JSONObject) doctorArray.get(i);
				@SuppressWarnings("unused")
				String name = (String) obj.get("DoctorName");
				if (obj.get(searchBy).equals(search)) {
					doctorName = (String) obj.get("DoctorName");
					patientArray = getPatientArray(doctorName, getAppointmentArrayName(), appointmentFilePath);
					if (patientArray == null) {
						addToExistingAppointment(doctorName, patientName, appointmentFilePath);
						break;
					} else {
						if (patientArray.size() < 5)
							break;
					}
				}
			}
			return doctorName;
		}

		/*
		 * method for getting patient array
		 */
		private JSONArray getPatientArray(String doctorName, String arrayName, String filePath)
				throws FileNotFoundException, IOException, ParseException {
			JSONArray appointmentArray = null;
			JSONArray patientArray = null;
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
			appointmentArray = (JSONArray) jsonObject.get(arrayName);
			for (int i = 0; i < appointmentArray.size(); i++) {
				JSONObject object = (JSONObject) appointmentArray.get(i);
				if (object.get("DoctorName").equals(doctorName)) {
					patientArray = (JSONArray) object.get("PatientArray");
					return patientArray;
				}
			}
			return patientArray;
		}

		@SuppressWarnings("unchecked")
		/*
		 * method to add appointments to the file
		 */
		private void addToExistingAppointment(String doctorName, String patientName, String filePath)
				throws FileNotFoundException, IOException, ParseException {
			JSONArray array = getArray(filePath, "Appointment");
			JSONArray patientArray = new JSONArray();
			JSONObject object = new JSONObject();
			patientArray.add(patientName);
			object.put("DoctorName", doctorName);
			object.put("PatientArray", patientArray);
			array.add(object);
			jsonObjectAppointment.put("Appointment", array);
			System.out.println(jsonObjectAppointment.toJSONString());
			writeToFile(jsonObjectAppointment, appointmentFilePath);
		}

		@SuppressWarnings("unchecked")
		/*
		 * method for ading appointments to the file
		 */
		private void addToAppointmentFile(String doctorName, String patientName) throws IOException {
			JSONObject object = new JSONObject();
			JSONArray patientArray = new JSONArray();
			patientArray.add(patientName);
			object.put("DoctorName", doctorName);
			object.put("PatientArray", patientArray);
			appointment.add(object);
			jsonObjectAppointment.put(getAppointmentArrayName(), appointment);
			writeToFile(jsonObjectAppointment, appointmentFilePath);
		}

		/*
		 * method for getting array of appointment
		 */
		public JSONArray getAppointmentArray(String filePath, String arrayName, String doctorName, String patientName)
				throws FileNotFoundException, IOException, ParseException {
			JSONArray array = null;
			JSONArray array1 = null;
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
			array = (JSONArray) jsonObject.get(arrayName);
			for (int i = 0; i < array.size(); i++) {
				JSONObject object = (JSONObject) array.get(i);
				if (object.get("DoctorName").equals(doctorName)) {
					array1 = (JSONArray) object.get("PatientArray");
					return array1;
				}
			}
			return array1;
		}

		/*
		 * method for checking if patient name exist in patient array
		 */
		private boolean checkPatientName(JSONArray array, String arrayName, String filePath, String patientName)
				throws FileNotFoundException, IOException, ParseException {
			JSONObject obj = null;
			JSONArray patientArray;
			JSONArray appointmentArray = getArray(filePath, arrayName);
			for (int i = 0; i < appointmentArray.size(); i++) {
				obj = (JSONObject) appointmentArray.get(i);
				patientArray = (JSONArray) obj.get("PatientArray");
				for (int j = 0; j < patientArray.size(); j++) {
					if (patientArray.get(j).equals(patientName)) {
						return true;
					}
				}
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		/*
		 * method for updating appointment file
		 */
		private void updateAppointmentFile(String doctorName, String patientName, JSONArray array)
				throws FileNotFoundException, IOException, ParseException {
			JSONObject updateObject = new JSONObject();
			if (array.size() < 5) {
				JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(appointmentFilePath));
				appointment = (JSONArray) jsonObject.get("Appointment");
				for (int i = 0; i < appointment.size(); i++) {
					updateObject = (JSONObject) appointment.get(i);
					if (updateObject.get("DoctorName").equals(doctorName)) {
						array.add(patientName);
						updateObject.put("PatientArray", array);
						jsonObjectAppointment.put("Appointment", appointment);
						break;
					}
				}
				System.out.println(jsonObjectAppointment.toJSONString());
				writeToFile(jsonObjectAppointment, appointmentFilePath);

			} else {
				System.out.println("Sorry..!!" + patientName + " today's Dr." + doctorName + " appointments "
						+ "are full..!! " + patientName + " tommorow's appointment is fixed for you");
			}
		}
	}

