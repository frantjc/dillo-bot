import {
    DefaultProps,
} from '.';

export interface LabelProps extends DefaultProps {
    horizontal: boolean;
    inputId: string;
}

export default function Label(props: LabelProps): JSX.Element;
