import {
    DefaultProps,
} from '.';

export interface OptionProps extends DefaultProps {
    selected: boolean;
    value: string | ReadonlyArray<string> | number;
}

export default function Option(props: OptionProps): JSX.Element;