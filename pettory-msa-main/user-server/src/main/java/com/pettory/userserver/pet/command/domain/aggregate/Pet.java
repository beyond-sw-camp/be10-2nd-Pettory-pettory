package com.pettory.userserver.pet.command.domain.aggregate;

import com.pettory.userserver.exception.NotFoundException;
import com.pettory.userserver.family.command.domain.aggregate.Family;
import com.pettory.userserver.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.userserver.user.command.domain.aggregate.User;
import com.pettory.userserver.walkingRecord.command.domain.aggregate.WalkingRecord;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE pet SET pet_state ='DELETE', pet_delete_datetime = NOW() where pet_id = ? AND pet_state != 'DELETE'")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;
    @Enumerated(value = EnumType.STRING)
    private PetType petType;
    private String petName;
    private String petBreed;
    private LocalDate petBirth;
    @Enumerated(value = EnumType.STRING)
    private PetGender petGender;
    private Boolean petNeuteringYn;
    private Long petWeight;
    private String petMemo;

    @CreatedDate
    private LocalDateTime petInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime petUpdateDatetime;
    private LocalDateTime petDeleteDatetime;

    @Enumerated(value = EnumType.STRING)
    private PetState petState = PetState.ACTIVE;
    // userId -- fk
    // familyId -- fk

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "family_id")
    Family family;

    @OneToMany(mappedBy = "pet")
    List<PetAccess> petAccesses = new ArrayList<>();

    @OneToMany(mappedBy = "pet")
    private List<WalkingRecord> walkingRecords = new ArrayList<>();

    @OneToMany(mappedBy = "pet")
    private List<FeedingRecord> feedingRecords = new ArrayList<>();

    public Pet(PetType petType, String petName, String petBreed, LocalDate petBirth, PetGender petGender, Boolean petNeuteringYn, Long petWeight, String petMemo) {

        this.petType = petType;
        this.petName = petName;
        this.petBreed = petBreed;
        this.petBirth = petBirth;
        this.petGender = petGender;
        this.petNeuteringYn = petNeuteringYn;
        this.petWeight = petWeight;
        this.petMemo = petMemo;
    }

    public static Pet create(PetType petType, String petName, String petBreed, LocalDate petBirth, PetGender petGender, Boolean petNeuteringYn, Long petWeight, String petMemo) {
        return new Pet(petType, petName, petBreed, petBirth, petGender, petNeuteringYn, petWeight, petMemo);
    }

    // 회원 엔터티에 반려동물을 추가하는 메소드
    public void addPetToUser(User user) {
        if (user == null) {
            throw new NotFoundException("회원을 찾을 수 없습니다.");
        }
        this.user = user;
    }

    // 가족 엔터티에 반려동물을 추가하는 메소드
    public void addPetToFamily(Family family) {
        if(family == null) {
            throw new NotFoundException("가족을 찾을 수 없습니다.");
        }
        this.family = family;
    }

    public void updatePetInfo(PetType petType, String petName, String petBreed, LocalDate petBirth, PetGender petGender, Boolean petNeuteringYn, Long petWeight, String petMemo) {
        if (petType != null) this.petType = petType;
        if (petName != null && !petName.trim().isEmpty()) this.petName = petName;
        if (petBreed != null && !petBreed.trim().isEmpty()) this.petBreed = petBreed;
        if (petBirth != null) this.petBirth = petBirth;
        if (petGender != null) this.petGender = petGender;
        if (petNeuteringYn != null) this.petNeuteringYn = petNeuteringYn;
        if (petWeight != null) this.petWeight = petWeight;
        if (petMemo != null && !petMemo.trim().isEmpty()) this.petMemo = petMemo;
    }

    // 반려동물의 상태를 삭제로 변경하는 메소드
    public void updatePetAsDelete() {
        this.petState = PetState.DELETE;
        this.petDeleteDatetime = LocalDateTime.now();
    }
}
