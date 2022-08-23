/* 샘플 OCR 데이터, 바운딩박스는 정확하지 않음 */

INSERT INTO ocr_data(raw_data_id, image_url) VALUES(1, 'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1288.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(2,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1289.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(3,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1290.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(4,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1291.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(5,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1292.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(6,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1293.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(7,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1294.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(8,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1295.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(9,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1296.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(10,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1297.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(11,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1298.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(12,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1299.JPG');
INSERT INTO ocr_data(raw_data_id, image_url) VALUES(13,'https://belloga-dev-s3-raw-data-bucket.s3.ap-northeast-2.amazonaws.com/ocr-data/5350037-2005-0001-1300.JPG');


/* 임의의 바운딩 박스 */
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(1, 'RECTANGLE', 10,10,10,20,20,10,20,20);

INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(2, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(2, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(3, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(3, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(4, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(4, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(5, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(5, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(6, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(6, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(7, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(7, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(8, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(8, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(9, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(9, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(10, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(10, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(11, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(11, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(12, 'RECTANGLE', 10,10,10,20,20,10,20,20);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(12, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(13, 'RECTANGLE', 30,30,30,50,50,30,50,50);
INSERT INTO ocr_bounding_box(ocr_data_id, ocr_annotation_type, left_top_x, left_top_y, left_down_x, left_down_y, right_top_x, right_top_y, right_down_x, right_down_y)
VALUES(13, 'RECTANGLE', 10,10,10,20,20,10,20,20);


/* 검증 결과 */
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 1, 1005, 88.2, '검증결과값1');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 2, 125, 83.2, '검증결과값2');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 3, 2015, 95.2, '검증결과값3');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 4, 123, 89.2, '검증결과값4');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 5, 112, 85.24, '검증결과값5');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 6, 142, 82.212, '검증결과값6');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 7, 1401, 81.1, '검증결과값7');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 8, 392, 83.4, '검증결과값8');
INSERT INTO ocr_bounding_box_labeled_result(enterprise_id, ocr_bounding_box_id, total_labeler_num, reliability, text_label)
VALUES('gildong', 9, 182, 92.1, '검증결과값9');