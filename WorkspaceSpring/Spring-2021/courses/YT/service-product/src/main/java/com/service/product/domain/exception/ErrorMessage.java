package com.service.product.domain.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorMessage {
  private String errorCode;
  private List<Map<String, String>> messages;
}
