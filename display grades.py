import csv  

def display_student_gpas(filename):
    # Open the CSV file for reading
    with open(filename, newline='') as file:
        # Create a CSV reader object
        reader = csv.reader(file)  

        # Print the header row with formatted columns
        print(f"{'ID':<5} {'Name':<20} {'GPA'}")
        print("-" * 35)  # Print a separator line

        # Loop through each row in the CSV file
        for row in reader:
            # Unpack the row into student_id, name, and gpa variables
            student_id, name, gpa = row
            
            # Print the student information in a formatted way
            print(f"{student_id:<5} {name:<20} {gpa}")

# Call the function and provide the CSV filename
display_student_gpas('grades.csv')