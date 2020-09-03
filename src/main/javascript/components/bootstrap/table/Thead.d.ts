import {
    DefaultProps,
} from '.';

export interface TrProps extends DefaultProps {
    color:
        | 'light'
        | 'dark';
}

export default function Tr(props: TrProps): JSX.Element;
