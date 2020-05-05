import * as React from 'react';
import ReactDOM from 'react-dom';

export interface IIndexProps {}

function Index(props: IIndexProps) {
  return <div>Hello, World</div>;
}

const react = document.getElementById('react');
ReactDOM.render(<Index />, react);
