

import java.util.List;
import java.util.Scanner;

import controller.PetsHelper;
import model.Pets;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static PetsHelper ph = new PetsHelper();

		private static void addAPet() {
			// TODO Auto-generated method stub
			System.out.print("Enter a name: ");
			String name = in.nextLine();
			System.out.print("Enter an type: ");
			String type = in.nextLine();
			System.out.print("Enter an owner: ");
			String owner = in.nextLine();
			Pets toAdd = new Pets(name, type, owner);
			ph.insertPet(toAdd);

		}

		private static void deleteAPet() {
			// TODO Auto-generated method stub
			System.out.print("Enter the name to delete: ");
			String name = in.nextLine();
			System.out.println("Enter the pets type to delete");
			String type = in.nextLine();
			System.out.println("Enter the pets owner to delete");
			String owner = in.nextLine();
			
			Pets toDelete = new Pets(name, type, owner);
			ph.deletePet(toDelete);
		}

		private static void editAPet() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Name");
			System.out.println("1 : Search by Type");
			System.out.println("2 : Search by Owner");
			int searchBy = in.nextInt();
			in.nextLine();
			List<Pets> foundPets;
			if (searchBy == 1) {
				System.out.print("Enter the Name: ");
				String nameName = in.nextLine();
				foundPets = ph.searchForPetByName(nameName);
			} else if(searchBy==2){
				System.out.println("Enter the Type");
				String typeName = in.nextLine();
				foundPets = ph.searchForPetByType(typeName);
			}else {
				System.out.println("Enter the Owner");
				String ownerName = in.nextLine();
				foundPets = ph.searchForPetByOwner(ownerName);
			}

			if (!foundPets.isEmpty()) {
				System.out.println("Found Results.");
				for (Pets p : foundPets) {
					System.out.println(p.getId()+": "+ p.getName()+": "+ p.getType()+": "+p.getOwner());
				}
				System.out.print("What number to edit: ");
				int idToEdit = in.nextInt();

				Pets toEdit = ph.searchForPetById(idToEdit);
				System.out.println("Retrieved " + toEdit.getName() + " Type " + toEdit.getOwner()+ " Owned by"+toEdit.getOwner());
				System.out.println("1 : Update Name");
				System.out.println("2 : Update Type");
				System.out.println("3 : Update Owner");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Name: ");
					String newName = in.nextLine();
					toEdit.setName(newName);
				} else if (update == 2) {
					System.out.print("New Type: ");
					String newType = in.nextLine();
					toEdit.setType(newType);
				} else if(update == 3) {
					System.out.println("New Owner :");
					String newOwner = in.nextLine();
					toEdit.setOwner(newOwner);
				}

				ph.updatePet(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome pets list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add a pet");
				System.out.println("*  2 -- Edit a pet");
				System.out.println("*  3 -- Delete a pet");
				System.out.println("*  4 -- View the pets");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAPet();
				} else if (selection == 2) {
					editAPet();
				} else if (selection == 3) {
					deleteAPet();
				} else if (selection == 4) {
					viewThePets();
				} else {
					ph.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewThePets() {
			// TODO Auto-generated method stub
			List<Pets> allPets = ph.showAllPets();
			for(Pets singlePet : allPets) {
				System.out.println(singlePet.returnPetDetails());
			}

		}

	}
