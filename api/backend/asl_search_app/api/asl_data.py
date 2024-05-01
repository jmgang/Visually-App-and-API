from dataclasses import dataclass

@dataclass
class ASLData:
    title: str
    description: str
    url: str
    video_url: str

    @staticmethod
    def from_dict(data):
        return ASLData(**data)