import * as React from 'react';
import ReactDOM from 'react-dom';
import Header from '../layout/header';
import 'mini.css';

export interface IIndexProps {}

function Index(props: IIndexProps) {
  return (
    <>
      <Header />
      <div>hello, world</div>
    </>
  );
}

const react = document.getElementById('react');
ReactDOM.render(<Index />, react);
