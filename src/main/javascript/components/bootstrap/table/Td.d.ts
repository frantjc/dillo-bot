import {
    DefaultProps,
} from '.';

export interface TdProps extends DefaultProps {
    onClick: () => void;
}

export default function Td(props: TdProps): JSX.Element;