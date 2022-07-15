/* OCR data 를 위한 테이블  */
CREATE TABLE ocr_data (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    raw_data_id BIGINT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    is_labeled BOOLEAN DEFAULT false
);

/* OCR 데이터는 여러 바운딩 박스를 가진다. */
CREATE TABLE ocr_bounding_box (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ocr_data_id BIGINT NOT NULL,
    ocr_annotation_type VARCHAR(63),
    left_top_x INTEGER,
    left_top_y INTEGER,
    left_down_x INTEGER,
    left_down_y INTEGER,
    right_top_x INTEGER,
    right_top_y INTEGER,
    right_down_x INTEGER,
    right_down_y INTEGER,
    FOREIGN KEY (ocr_data_id) REFERENCES ocr_data (id)
);