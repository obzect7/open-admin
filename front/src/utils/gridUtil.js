import {saveCmCode} from "@/services/commoncode";

export default {
    install(Vue){
        Vue.prototype.$getCudData = function(grid,requireArr){
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
    }
}