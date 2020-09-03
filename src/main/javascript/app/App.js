import React from 'react';
import { DndProvider } from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';

import { getDiscordUsers, getGitHubUsers, getDiscordChannels } from '../api';

import { Svg } from '../svgs';

import { Navbar, Nav, NavItem } from '../components/bootstrap/navbar';

import { Quadrant, Quadrants } from '../components/quadrant';

import { Toolbar, Table, TableSpace } from '../components/table';

const Header = () => (
  <Navbar brand="DilloBot">
    <Nav direction="right">
      <NavItem href="https://reactjs.org">
        Powered by React
        <Svg name="react" className="react-logo" alt="react-logo" />
      </NavItem>
    </Nav>
  </Navbar>
);

const tables = [
  {
    name: 'discord_users',
    getData: () => getDiscordUsers(),
    onTableClick: (row, column) => {},
  },
  {
    name: 'github_users',
    getData: () => getGitHubUsers(),
    onTableClick: (row, column) => {},
  },
  {
    name: 'discord_channels',
    getData: () => getDiscordChannels(),
    onTableClick: (row, column) => {},
  },
  {
    name: 'no_table',
  },
];

const App = () => (
  <DndProvider backend={HTML5Backend}>
    <Header />
    <Toolbar>
      {tables.map((table, key) => (
        <Table table={table} key={key} />
      ))}
    </Toolbar>
    <Quadrants>
      {[0, 1, 2, 3].map((key) => (
        <Quadrant key={key}>
          <TableSpace />
        </Quadrant>
      ))}
    </Quadrants>
  </DndProvider>
);

export default App;
