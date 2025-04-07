package org.example.mappers;

public interface Mapper <A, B>{
    B mapToDto(A a);
    A mapFromDto(B b);
}
