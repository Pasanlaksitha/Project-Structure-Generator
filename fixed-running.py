import os

def print_directory_tree(root_dir, indent=""):
    """Recursively prints the directory tree starting from root_dir."""
    items = os.listdir(root_dir)
    for idx, item in enumerate(items):
        path = os.path.join(root_dir, item)
        is_last = idx == len(items) - 1

        # Print the item
        if os.path.isdir(path):
            print(f"{indent}├── {item}/")
            print_directory_tree(path, indent + "│   " if not is_last else indent + "    ")
        else:
            print(f"{indent}├── {item}")

def write_directory_tree(root_dir, file, indent=""):
    """Recursively writes the directory tree starting from root_dir to a file."""
    items = os.listdir(root_dir)
    for idx, item in enumerate(items):
        path = os.path.join(root_dir, item)
        is_last = idx == len(items) - 1

        # Write the item to the file
        if os.path.isdir(path):
            file.write(f"{indent}├── {item}/\n")
            write_directory_tree(path, file, indent + "│   " if not is_last else indent + "    ")
        else:
            file.write(f"{indent}├── {item}\n")

def main():
    project_root = ""  # Replace with your project's root directory
    print(f"{project_root}/")
    print_directory_tree(project_root)

    
    with open("project_structure.txt", "w", encoding="utf-8") as file:
        file.write(f"{project_root}/\n")
        write_directory_tree(project_root, file)

if __name__ == "__main__":
    main()
