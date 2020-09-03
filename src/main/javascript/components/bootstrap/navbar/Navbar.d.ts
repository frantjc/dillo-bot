import {
    DefaultProps,
} from '.';

export interface NavbarProps extends DefaultProps {
    brand: React.ReactNode;
}

export default function Navbar(props: NavbarProps): JSX.Element;
