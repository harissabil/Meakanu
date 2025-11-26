<div align="center">
  <img src="https://github.com/harissabil/Meakanu/blob/master/app/src/main/ic_launcher-playstore.png" width="150" alt="Centered Image">
  <h1>Meakanu: Plant Identifier</h1>
</div>

Explore the rich diversity of plants, leaves, flowers, fruits, and trees easily. Simply take a photo or upload an image of any plant you encounter, and let Meakanu work its magic. Within seconds, you'll receive accurate and detailed information about the plant's name, species, and key characteristics. Whether you're a gardening enthusiast, a hiker, or someone who appreciates the beauty of the natural world, Meakanu will elevate your experience. Learn and discover interesting facts about various plants.

This project was a submission for [IDCamp X KADIN Challenge 2023](https://idcamp.ioh.co.id/news/940/selamat-kepada-pemenang-idcamp-x-kadin-challenge-2023-menuju-indonesia-emas-pada-tahun-2045).

## Screenshots

<table>
  <tbody>
    <tr>
      <td><img src="assets/screenshot/home.jpg?raw=true"/></td>
      <td><img src="assets/screenshot/identify.jpg?raw=true"/></td>
      <td><img src="assets/screenshot/result.jpg?raw=true"/></td>
    </tr>
    <tr>
      <td><img src="assets/screenshot/plant.jpg?raw=true"/></td>
      <td><img src="assets/screenshot/detail.jpg?raw=true"/></td>
      <td><img src="assets/screenshot/articles.jpg?raw=true"/></td>
    </tr>
  </tbody>
</table>

## Features

Plant Identification — Snap a picture or select an image from your gallery, and Meakanu will identify the plant instantly.

Extensive Plant Database — Access a vast collection of plants, flowers, fruits, leaves, and trees from around the globe.

Personalize — Edit the plant name you recently searched based on the identification result to personalize your collection.

Plant Articles — Expand your knowledge and appreciation for nature's wonders as you explore engaging content.

## Installation

To install Meakanu, there are two options available:

### Option 1: Download from Play Store

Visit the [Meakanu Play Store page](https://play.google.com/store/apps/details?id=com.harissabil.meakanu).

### Option 2: Build from Source

1. Clone or download the project and open it in Android Studio.
2. Create a `local.properties` file in the project root folder if it doesn't exist.
3. Add the following lines to the `local.properties` file:

```android
...

BASE_URL_TREFLE = "http://trefle.io/api/v1/"
API_KEY_TREFLE = "your key here"

BASE_URL_PLANTNET = "https://my-api.plantnet.org/v2/"
API_KEY_PLANTNET = "your key here"

BASE_URL_NEWS = "https://newsapi.org/v2/"
API_KEY_NEWS = "your key here"
```

4. Get your API keys from [Trefle](https://trefle.io/), [PlantNet](https://my.plantnet.org/), and [News API](https://newsapi.org/), and replace the placeholders with your keys.
5. Sync the project with Gradle and run the app on an Android emulator or a physical Android device.

## Open Source Credits

- [PlantNet](https://my.plantnet.org/) for plant identification.
- [Trefle](https://trefle.io/) for plant data.
- [News API](https://newsapi.org/) for plant articles.

## License

Meakanu is open-source and released under the [MIT License](LICENSE).
