/* OCR data 를 위한 테이블  */
CREATE TABLE ocr_data (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    enterprise_id VARCHAR(127),
    project_id BIGINT NOT NULL,
    raw_data_id BIGINT NOT NULL,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    image_url VARCHAR(255) NOT NULL
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
    is_labeled BOOLEAN DEFAULT false,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    FOREIGN KEY (ocr_data_id) REFERENCES ocr_data (id)
);

/* OCR 이미지 바운딩 박스 라벨링 테이블 */
CREATE TABLE labeled_ocr_data (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    labeler_id VARCHAR(127) NOT NULL,
    labeling_uuid VARCHAR(127) NOT NULL,
    ocr_bounding_box_id BIGINT NOT NULL,
    text_label VARCHAR(511),
    labeling_verification_status VARCHAR(63), /* 라벨링 검증 상태: WAITING, SUCCESS, FAIL */
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    FOREIGN KEY (ocr_bounding_box_id) REFERENCES ocr_bounding_box (id)
);

/* OCR 바운딩박스 검증 후 검증 성공 시 결과 테이블 */
CREATE TABLE ocr_bounding_box_labeled_result (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    project_id BIGINT,
    enterprise_id VARCHAR(127),
    ocr_bounding_box_id BIGINT,
    total_labeler_num BIGINT,
    reliability DOUBLE,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    text_label VARCHAR(511)
);