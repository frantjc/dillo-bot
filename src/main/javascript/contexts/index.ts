import { createContext } from 'react';

type Menu = {}

const MenuContext = createContext<Menu>({});

export { MenuContext };
