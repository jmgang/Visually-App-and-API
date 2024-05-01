import pandas as pd
import json

sl_descriptions_df = pd.read_excel(r'D:\code\other repos\VisuAlly\backend\sl_translation_app\resources\sign_language_descriptions_v2.xlsx')

classes = sl_descriptions_df['Sign Language Meaning'].unique().tolist()
unique_classes = sl_descriptions_df['Sign Language Meaning'].unique().tolist()

with open(r'D:\code\other repos\VisuAlly\backend\sl_translation_app\resources\sl_language_descriptions_v2.jsonl', 'w') as file:
    for index, row in sl_descriptions_df.iterrows():
        user_content = row['Description']
        model_content = row['Sign Language Meaning']

        # Construct JSON object
        json_data = {
            "messages": [
                {
                    "role": "system",
                    "content": f"You should classify the text into one of the following classes: {unique_classes}"
                },
                {
                    "role": "user",
                    "content": user_content
                },
                {
                    "role": "model",
                    "content": model_content
                }
            ]
        }

        # Write JSON object to file
        file.write(json.dumps(json_data) + '\n')