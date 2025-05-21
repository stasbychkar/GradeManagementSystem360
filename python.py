import os
import pandas as pd
import numpy as np
import scipy
import matplotlib.pyplot as plt
import matplotlib
import statsmodels.api as sm
import statsmodels.formula.api as smf
import argparse
from pathlib import Path

matplotlib.rcParams.update({'font.size': 18})
#Generates a histogram based on the criteria given
def GenerateHist(criteria):
    if(criteria == "GPA"):
        binCount = 8

    # Plot and grab the patches
    counts, bins, patches = plt.hist(
        gradeDF[criteria].dropna(),
        bins=binCount,
        edgecolor='black',
        label=criteria
    )
    
    cmap = plt.get_cmap('Blues')

    n = len(patches)
    colors = cmap(np.linspace(0.2, 0.8, n))

    for patch, color in zip(patches, colors):
        patch.set_facecolor(color)
        
    plt.gca().set(title = criteria + " for all students", ylabel = "Count", xlabel = criteria)
    plt.legend()
    plt.show()



script_dir = Path(__file__).resolve().parent

csv_path = script_dir / "Grades.csv"

gradeDF = pd.read_csv(csv_path)
gradeDF = gradeDF[pd.notna(gradeDF['StuID'])]

parser = argparse.ArgumentParser()
subparsers = parser.add_subparsers(dest="cmd", required=True)


# GenerateHist sub-command
p_GH = subparsers.add_parser("GenerateHist", help="Run GenerateHist")
p_GH.add_argument("criteria", type=str)

args = parser.parse_args()

if args.cmd == "GenerateHist":
    GenerateHist(args.criteria)


    
