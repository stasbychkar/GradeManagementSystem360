import pandas as pd
import numpy as np
import matplotlib
matplotlib.use('TkAgg')
import matplotlib.pyplot as plt
import argparse
from pathlib import Path
import sys

matplotlib.rcParams.update({'font.size': 18})

# Generates a histogram based on the criteria given
def GenerateHist(criteria):
    # Sets bin count
    if criteria == "GPA":
        binCount = 8

    # TODO: Add more bincount based on different criteria

    # Plot and grab the patches
    counts, bins, patches = plt.hist(
        gradeDF[criteria].dropna(),
        bins=binCount,
        edgecolor='black',
        label=criteria
    )

    # Add colors to the histogram
    cmap = plt.get_cmap('Blues')
    n = len(patches)
    colors = cmap(np.linspace(0.2, 0.8, n))
    for patch, color in zip(patches, colors):
        patch.set_facecolor(color)

    # Set table labels and show plot
    plt.gca().set(title=f"{criteria} for all students", ylabel="Count", xlabel=criteria)
    plt.show()


# Set up CSV file path
if getattr(sys, 'frozen', False) and hasattr(sys, '_MEIPASS'):
    bundle_dir = Path(sys.executable).parent
else:
    bundle_dir = Path(__file__).parent

csv_path = bundle_dir / "Grades.csv"

# Load DataFrame
gradeDF = pd.read_csv(csv_path)
gradeDF = gradeDF[pd.notna(gradeDF['ID'])]  # Exclude students without ID

# Only run CLI if file is executed directly
if __name__ == "__main__":
    # Set up CLI parser
    parser = argparse.ArgumentParser()
    subparsers = parser.add_subparsers(dest="cmd", required=True)

    # GenerateHist sub-command
    p_GH = subparsers.add_parser("GenerateHist", help="Run GenerateHist")
    p_GH.add_argument("criteria", type=str)

    # Parse and execute
    args = parser.parse_args()
    if args.cmd == "GenerateHist":
        GenerateHist(args.criteria)
