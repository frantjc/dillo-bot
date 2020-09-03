import {
    DefaultProps,
} from '.';

export interface FormGroupProps extends DefaultProps {
    row: boolean | 'true' | 'false',
}

export default function FormGroup(props: FormGroupProps): JSX.Element;
