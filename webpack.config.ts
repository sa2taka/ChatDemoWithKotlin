import * as path from 'path';
import glob from 'glob';
import { Configuration, Options } from 'webpack';

const targets = glob.sync(`${__dirname}/src/main/js/pages/*.tsx`);
const entries: Record<string, string> = {};
targets.forEach((value) => {
  const re = new RegExp(`${__dirname}/src/main/js/pages`);
  const key = value.replace(re, '').replace(/\.tsx$/, '');
  entries[key] = value;
});

const config: Configuration = {
  context: path.join(__dirname, 'src'),
  entry: entries,
  devtool: 'source-map' as Options.Devtool,
  cache: true,
  mode: 'development',
  output: {
    path: path.join(__dirname, 'src/main/resources/static/js/'),
    filename: '[name].js',
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        exclude: /node_modules/,
        use: [{ loader: 'ts-loader' }],
      },
    ],
  },
  resolve: {
    extensions: ['.ts', '.tsx', '.js', 'jsx'],
  },
  devServer: {
    contentBase: path.join(__dirname, 'static'),
    host: '0.0.0.0',
  },
};

export default config;
