import {
    DefaultProps,
} from '.';

export interface NavDropdownProps extends DefaultProps {
    href: URL;
    dropdown: React.ReactNode;
}

export default function NavDropdown(props: NavDropdownProps): JSX.Element;