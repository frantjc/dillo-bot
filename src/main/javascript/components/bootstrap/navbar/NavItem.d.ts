import {
    DefaultProps,
} from '.';

export interface NavItemProps extends DefaultProps {
    href: URL;
}

export default function NavItem(props: NavItemProps): JSX.Element;