import * as path from 'path';
import glob from 'glob';
import { Configuration, Options } from 'webpack';

const targets = glob.sync(`${__dirname}/src/main/js/pages/*.tsx`);
const entries: Record<string, string> = {};

// buildPathの2つに向けてpages内のファイルのビルドを行っている
// buildPathに送ることでHotReloadができる
// srcPathに送ることでgradleでのビルド時に自動的にビルドされる
// NODE_ENVでわけてもいいかもしれないが、ひとまずこういう設計
targets.forEach((value) => {
  const re = new RegExp(`${__dirname}/src/main/js/pages`);
  const key = value.replace(re, '').replace(/\.tsx$/, '');
  const srcPath = 'src/main/resources/static/js';
  const buildPath = 'build/resources/main/static/js';
  entries[srcPath + key] = value;
  entries[buildPath + key] = value;
});

const env =
  process.env.NODE_ENV === 'production' ? 'production' : 'development';

const config: Configuration = {
  context: path.join(__dirname, 'src'),
  entry: entries,
  devtool: env === 'production' ? false : ('source-map' as Options.Devtool),
  cache: true,
  mode: env,
  output: {
    path: __dirname,
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
