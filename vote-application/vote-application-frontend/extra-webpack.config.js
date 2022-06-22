import webpack from "webpack";

module.exports = {
  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        BACKEND_ENDPOINT: JSON.stringify(process.env.BACKEND_ENDPOINT)
      }
    })
  ]
}
