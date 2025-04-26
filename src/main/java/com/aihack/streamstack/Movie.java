package com.aihack.streamstack;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    String title;
    String description;
}
