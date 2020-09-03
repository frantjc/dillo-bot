import {
    DefaultProps,
} from '.';

export interface DropdownItemProps extends DefaultProps {
    href: URL;
}

export default function DropdownItem(props: DropdownItemProps): JSX.Element;