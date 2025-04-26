package com.aihack.streamstack;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    String id;
    String title;
    String description;
}
