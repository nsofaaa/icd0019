package generics.converter;

public class CompositeConverter<T, M, U> implements Converter<T, U> {

    private final Converter<T, M> first;
    private final Converter<M, U> second;

    public CompositeConverter(Converter<T, M> first, Converter<M, U> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public U convert(T input) {
        return second.convert(first.convert(input));
    }
}