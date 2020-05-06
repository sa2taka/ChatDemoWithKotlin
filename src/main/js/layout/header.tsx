import React, { ReactElement } from 'react';
import 'mini.css';

interface Props {}

export default function header({}: Props): ReactElement {
  return (
    <header>
      <h1>Chat Demo</h1>
    </header>
  );
}
