import {saveCmCode} from "@/services/commoncode";

export default {
    install(Vue){
        /*
         paramter 정의
         grid = vue grid 객체
         requireArr = 필수입력 dataField
         해당 함수는 추가,수정,삭제 된 row 정보를 모두 담아서 배열로 return 한다.
         행의 상태는 row_status 항목에 담겨져 있다.
         */
        Vue.prototype.$gridGetCudData = function(grid,requireArr){
            const isValid = grid.validateChangedGridData(requireArr, "필수입력 입니다.");

            if (isValid) {
                console.log(' this.$store.state.account.user.username===', this.$store.state.account.user.username)
                // let items = grid.getCheckedRowItems();
                // console.log("items===", JSON.stringify(items))

                let addedRowItems = grid.getAddedRowItems(); // 추가된 행 아이템들(배열)
                let editedRowItems = grid.getEditedRowItems(); // 수정된 행 아이템들(배열) (수정되지 않은 칼럼들의 값도 가지고 있음)
                let removedRowItems = grid.getRemovedItems(); // 삭제된 행 아이템들(배열)

                // console.log("addedRowItems===", JSON.stringify(addedRowItems))
                // console.log("editedRowItems===", JSON.stringify(editedRowItems))
                // console.log("removedRowItems===", JSON.stringify(removedRowItems))

                let data = [];
                if (addedRowItems.length > 0) {
                    for (let i = 0; i < addedRowItems.length; i++) {
                        let addItem = addedRowItems[i]
                        Object.assign(addItem, {['row_status']: 'I'})
                        Object.assign(addItem, {['regId']: this.$store.state.account.user.username})
                        Object.assign(addItem, {['modId']: this.$store.state.account.user.username})
                        //console.log("editedItem==", editedItem)
                        data.push(addItem)
                    }
                    //data.add = addedRowItems;
                }
                if (editedRowItems.length > 0) {
                    for (let i = 0; i < editedRowItems.length; i++) {
                        let editedItem = editedRowItems[i]
                        Object.assign(editedItem, {['row_status']: 'U'})
                        Object.assign(editedItem, {['modId']: this.$store.state.account.user.username})
                        //console.log("editedItem==", editedItem)
                        data.push(editedItem)
                    }
                    //data.update = editedRowItems;
                }
                if (removedRowItems.length > 0) {
                    for (let i = 0; i < removedRowItems.length; i++) {
                        let removeItem = removedRowItems[i]
                        Object.assign(removeItem, {['row_status']: 'D'})
                        //console.log("editedItem==", editedItem)
                        data.push(removeItem)
                    }
                    //data.remove = removedRowItems;
                }
                // if (data.add || data.update || data.remove) {
                if (data.length == 0) {
                    this.$message.warn('추가, 수정, 삭제된 행이 없습니다.', 3)
                    return [];
                }else{
                    return data;
                }

            }
        }

        Vue.prototype.$gridEditable = function(grid,event,dataFields){
            if(dataFields.indexOf(event.dataField) > -1) {
                // rowIdField 설정 값 얻기
                const rowIdField = grid.getProp("rowIdField");
                console.log('rowIdField===',rowIdField)
                console.log('event === ',event)
                // 추가된 행 아이템인지 조사하여 추가된 행인 경우만 에디팅 진입 허용
                if(grid.isAddedById(event.item[rowIdField])) {
                    return true
                } else {
                    return false // false 반환하면 기본 행위 안함(즉, cellEditBegin 의 기본행위는 에디팅 진입임)
                }
            }else{
                return true // 다른 필드들은 편집 허용
            }


        }
    }
}