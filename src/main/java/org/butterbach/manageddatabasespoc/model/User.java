package org.butterbach.manageddatabasespoc.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@RequiredArgsConstructor
@ToString
public class User {

    private final UUID uuid;
    private final String name;

}
