import time
from vertexai.preview import tuning
from vertexai.preview.tuning import sft
from vertexai.preview.generative_models import GenerativeModel

import vertexai
vertexai.init(project='axiomatic-treat-421318', location='us-central1')

# sft_tuning_job = sft.train(
#     source_model="gemini-1.0-pro-002",
#     train_dataset="gs://visually-app/finetuning/sl_language_descriptions_v2.jsonl"
# )
# # Polling for job completion
# while not sft_tuning_job.has_ended:
#     time.sleep(60)
#     sft_tuning_job.refresh()
#
# print(sft_tuning_job.tuned_model_name)
# print(sft_tuning_job.tuned_model_endpoint_name)
# print(sft_tuning_job.experiment)

sft_tuning_job = sft.SupervisedTuningJob("projects/axiomatic-treat-421318/locations/us-central1/tuningJobs/7652610309529534464")
tuned_model = GenerativeModel(sft_tuning_job.tuned_model_endpoint_name)
print(tuned_model.generate_content('''
You should classify the text into one of the following classes: ['Help', 'Thank you', 'Deaf', 'Afternoon', 'Good morning']. 
Your response should only be the classified class.
You are a sign language expert. Given the following descriptive sequence of actions made by a signer. Determine the meaning of the sign language.

1. The signer raises their right hand, with their index finger extended and the remaining fingers closed, and places the tip of their index finger on their chin. 
2. They then move their right index finger to touch the area in front of their mouth, maintaining the same hand shape.
3. Finally, they move their right index finger to touch their ear, keeping the hand shape consistent throughout the sign. '''))
