const webpack = require('webpack');

console.log("==================================== " + process.env.BACKEND_ENDPOINT)
module.exports = {
  plugins: [
    new webpack.DefinePlugin({
      $ENV: {
        BACKEND_ENDPOINT: JSON.stringify(process.env.BACKEND_ENDPOINT),
        DATABASE_ENDPOINT: JSON.stringify(process.env.DATABASE_ENDPOINT)
      }
    })
  ]
};