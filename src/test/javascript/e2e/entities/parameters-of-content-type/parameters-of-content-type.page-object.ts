import { browser, ExpectedConditions, element, by, ElementFinder } from 'protractor';

export class ParametersOfContentTypeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-parameters-of-content-type div table .btn-danger'));
  title = element.all(by.css('jhi-parameters-of-content-type div h2#page-heading span')).first();

  async clickOnCreateButton(timeout?: number) {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(timeout?: number) {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ParametersOfContentTypeUpdatePage {
  pageTitle = element(by.id('jhi-parameters-of-content-type-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async save(timeout?: number) {
    await this.saveButton.click();
  }

  async cancel(timeout?: number) {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ParametersOfContentTypeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-parametersOfContentType-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-parametersOfContentType'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
