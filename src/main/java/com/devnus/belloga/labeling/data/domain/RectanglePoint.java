package com.devnus.belloga.labeling.data.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.List;

/**
 * 사각형 포인트에 대한 임베디드 타입
 */
@Embeddable
@Getter
@NoArgsConstructor // 임베디드는 기본생성자가 있어야함
public class RectanglePoint {
    @Column(name = "left_top_x")
    private Integer leftTopX;
    @Column(name = "left_top_y")
    private Integer leftTopY;
    @Column(name = "left_down_x")
    private Integer leftDownX;
    @Column(name = "left_down_y")
    private Integer leftDownY;
    @Column(name = "right_top_x")
    private Integer rightTopX;
    @Column(name = "right_top_y")
    private Integer rightTopY;
    @Column(name = "right_down_x")
    private Integer rightDownX;
    @Column(name = "right_down_y")
    private Integer rightDownY;

    @Builder
    public RectanglePoint(Integer leftTopX, Integer leftTopY, Integer leftDownX, Integer leftDownY, Integer rightTopX, Integer rightTopY, Integer rightDownX, Integer rightDownY) {
        this.leftTopX = leftTopX;
        this.leftTopY = leftTopY;
        this.leftDownX = leftDownX;
        this.leftDownY = leftDownY;
        this.rightTopX = rightTopX;
        this.rightTopY = rightTopY;
        this.rightDownX = rightDownX;
        this.rightDownY = rightDownY;
    }

    /**
     * x 좌표만 따로 리스트로 반환한다. DTO로 응답 시 사용 예정
     * @return X좌표 리스트
     */
    public List<Integer> getAllPointXByList() {
        return List.of(leftTopX, leftDownX, rightTopX, rightDownX);
    }

    /**
     * y 좌표만 따로 리스트로 반환한다. DTO로 응답 시 사용 예정
     * @return Y좌표 리스트
     */
    public List<Integer> getAllPointYByList() {
        return List.of(leftTopY, leftDownY, rightTopY, rightDownY);
    }
}
