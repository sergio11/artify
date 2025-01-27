package com.dreamsoftware.artify.data.repository.mapper

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.dto.CreateInquizeDTO
import com.dreamsoftware.artify.domain.model.CreateInquizeBO
import com.dreamsoftware.artify.domain.model.InquizeMessageRoleEnum

internal class CreateInquizeMapper: IBrownieOneSideMapper<CreateInquizeBO, CreateInquizeDTO> {
    override fun mapInListToOutList(input: Iterable<CreateInquizeBO>): Iterable<CreateInquizeDTO> =
        input.map(::mapInToOut)

    override fun mapInToOut(input: CreateInquizeBO): CreateInquizeDTO = with(input) {
        CreateInquizeDTO(
            uid = uid,
            userId = userId,
            imageUrl = imageUrl,
            imageDescription = imageDescription,
            question = question,
            questionRole = InquizeMessageRoleEnum.USER.name,
            answer = answer,
            answerRole = InquizeMessageRoleEnum.MODEL.name
        )
    }
}