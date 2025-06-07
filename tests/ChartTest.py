import unittest
import pandas as pd
import numpy as np
import sys
from pathlib import Path
from matplotlib import pyplot as plt
from unittest.mock import patch
sys.path.append(str(Path(__file__).resolve().parent.parent))
from charts import GenerateHist  

class TestGenerateHist(unittest.TestCase):

    @patch("charts.plt.show")  # Prevents actual window from popping up
    def test_generate_hist_valid_column(self, mock_show):
        # Create a mock gradeDF in scope of GenerateHist
        test_df = pd.DataFrame({
            'ID': [1, 2, 3, 4],
            'GPA': [3.5, 3.0, 4.0, 2.5]
        })

        with patch("charts.gradeDF", test_df):
            GenerateHist("GPA")
            self.assertTrue(mock_show.called)

    def test_generate_hist_invalid_column(self):
        test_df = pd.DataFrame({
            'ID': [1, 2, 3, 4],
            'GPA': [3.5, 3.0, 4.0, 2.5]
        })

        with patch("charts.gradeDF", test_df):
            with self.assertRaises(KeyError):
                GenerateHist("NotARealColumn")

if __name__ == '__main__':
    unittest.main()
