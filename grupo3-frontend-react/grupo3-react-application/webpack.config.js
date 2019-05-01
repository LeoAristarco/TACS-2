const webpack = require('webpack')
const path = require('path')

module.exports = {
    entry  : {
        main: ['babel-polyfill', path.join(__dirname, 'src/main.jsx')],
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'babel-loader',
                options: {
                    presets: ['env']
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.jsx?$/,
                use: {
                    loader: 'babel-loader',
                    query: {
                        cacheDirectory: true,
                        presets: ['react', ['es2015', {modules: false}], 'stage-0'],
                        compact: false
                    }
                },
                exclude: /node_modules/,
            }
        ]
    },
    resolve: {
        modules: [path.resolve(__dirname, 'src'), path.resolve(__dirname, 'node_modules')],
        extensions: ['.js', '.jsx']
    },
    output : {
        publicPath: path.resolve(__dirname, 'build/'),
        filename: 'build.js'
    },
    devServer: {
        contentBase: path.join(__dirname, 'public'),
        port: 8008,
        publicPath: 'http://localhost:8008/build',
        historyApiFallback: true
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoEmitOnErrorsPlugin()
    ]
}