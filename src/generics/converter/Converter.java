package generics.converter;

public interface Converter<T, U> {
    U convert(T input);
}