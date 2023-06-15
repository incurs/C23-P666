import os

from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello_world():
    """Example Hello World route."""
    name = os.environ.get("NAME", "World")
    return f"Hello {name}!"


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))

# import os
# os.environ['IF_CPP_MIN_LEVEL'] = '2'

# import io
# import tensorflow
# from tensorflow import keras
# from numpy import np
# from PIL import Image
# from flask import Flask, request, jsonify

# # label model.h5 pake keras
# model = keras.models.load_model("model.h5")
# # label yg ada di dalam model.h5
# label = ["klasifikasi yang diberikan acne"]

# app = Flask(__name__)

# # function untuk memprediksi pada gambar yg diinput
# def predict_label(image):
#     i = np.asarray(image)/255
#     # sesuaikan dengan input_size yang digunakan pada model
#     i = i.reshape(1,299,299,3)
#     pred = model.predict(i)
#     result = label[argmac(pred)]
#     return result

# @app.route("/predict", methods=('GET','POST'))
# def index():
#     file = request.files.get('file')
#     if file is None or file.filename == "":
#         return jsonify({"error": "no files"})
#     image_bytes = file.read()
#     img = Image.open(io.BytesIO(image_bytes))
#     img = img.resize((299,299), Image.NEAREST)
#     pred_img = predict_label
#     return pred_img

# if __name__ == "__main__":
#     app.run(debug=True)
